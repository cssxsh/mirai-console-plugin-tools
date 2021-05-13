package xyz.cssxsh.mirai.plugin

import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageSource.Key.quote

suspend fun <T : CommandSenderOnMessage<*>> T.sendMessage(block: suspend T.(Contact) -> Message): Boolean {
    return runCatching {
        block(fromEvent.subject)
    }.onSuccess {
        sendMessage(fromEvent.message.quote() + it)
    }.onFailure {
        sendMessage(fromEvent.message.quote() + it.toString())
    }.isSuccess
}