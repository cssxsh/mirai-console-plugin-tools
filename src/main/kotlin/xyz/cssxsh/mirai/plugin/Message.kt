package xyz.cssxsh.mirai.plugin

import kotlinx.coroutines.delay
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import net.mamoe.mirai.message.data.toPlainText
import kotlin.time.*

val SendLimit = """本群每分钟只能发\d+条消息""".toRegex()

@ExperimentalTime
suspend fun <T : CommandSenderOnMessage<*>> T.sendMessage(block: suspend T.(Contact) -> Message): Boolean {
    return runCatching {
        block(fromEvent.subject)
    }.onSuccess { message ->
        quoteReply(message)
    }.onFailure {
        when {
            SendLimit.containsMatchIn(it.message.orEmpty()) -> {
                delay((1).toDuration(DurationUnit.MINUTES))
                quoteReply(SendLimit.find(it.message!!)!!.value)
            }
            else -> {
                quoteReply("发送消息失败， ${it.message}")
            }
        }
    }.isSuccess
}

suspend fun CommandSenderOnMessage<*>.quoteReply(message: Message) = sendMessage(fromEvent.message.quote() + message)

suspend fun CommandSenderOnMessage<*>.quoteReply(message: String) = quoteReply(message.toPlainText())