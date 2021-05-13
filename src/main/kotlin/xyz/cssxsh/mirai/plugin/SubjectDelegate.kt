package xyz.cssxsh.mirai.plugin

import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.contact.Contact
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SubjectDelegate<T>(private val default: (Contact) -> T) : ReadWriteProperty<CommandSenderOnMessage<*>, T> {
    private val map: MutableMap<Contact, T> = mutableMapOf()

    override fun setValue(thisRef: CommandSenderOnMessage<*>, property: KProperty<*>, value: T) {
        map[thisRef.fromEvent.subject] = value
    }

    override fun getValue(thisRef: CommandSenderOnMessage<*>, property: KProperty<*>): T {
        return map.getOrPut(thisRef.fromEvent.subject) { default(thisRef.fromEvent.subject) }
    }
}