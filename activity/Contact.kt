package com.kintekhaiti.appcontact


class Contact {
    var id: Int? = -1
    var firstname: String? = ""
    var lastname: String? = ""
    var phone: String? = ""
    constructor(id: Int?, firstname: String?, lastname: String?, phone: String?) {
        this.id = id
        this.firstname = firstname
        this.lastname = lastname
        this.phone = phone
    }
}
