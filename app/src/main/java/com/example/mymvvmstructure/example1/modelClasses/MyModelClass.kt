package com.example.mymvvmstructure.example1.modelClasses

class MyModelClass {
    var id: String? = null
    var name: String? = null
    var phone: String? = null
    var address: String? = null

    constructor(id: String?, name: String?, phone: String?, address: String?) {
        this.id = id
        this.name = name
        this.phone = phone
        this.address = address
    }


    constructor(
        id: String?, name: String?,
        phone: String?
    ) {

        this.id = id
        this.name = name
        this.phone = phone
    }


}