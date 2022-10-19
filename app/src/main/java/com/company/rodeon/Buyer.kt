package com.company.rodeon

class Buyer(val name:String?="",val phone:String?="",val pass:String?="") {

    companion object{
        var currentuser:Buyer?=null
    }
}