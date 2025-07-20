package behavior.iterator.profile

/**
 * Social profile data class with contact management
 */
data class Profile(
    val email: String,
    val name: String,
    private val contactsMap: MutableMap<String, MutableList<String>> = mutableMapOf()
) {

    constructor(email: String, name: String, vararg contacts: String) : this(email, name) {
        // Parse contact list from "type:email" pairs
        contacts.forEach { contact ->
            val parts = contact.split(":", limit = 2)
            val contactType = if (parts.size == 1) "friend" else parts[0]
            val contactEmail = if (parts.size == 1) parts[0] else parts[1]

            contactsMap.getOrPut(contactType) { mutableListOf() }.add(contactEmail)
        }
    }

    fun getContacts(contactType: String): List<String> {
        return contactsMap.getOrPut(contactType) { mutableListOf() }
    }
}
