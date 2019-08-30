package com.hysyyl.bloodapp.model

class Address {
    var Province: String = ""
    var City: String = ""
    var County: String = ""
    var Street: String = ""
    var Longitude: String = ""
    var Latitude: String = ""

    fun getAddress(addStreet: Boolean): String {
        return if (addStreet) {
            Province + City + County + Street
        } else {
            Province + City + County
        }
    }
}