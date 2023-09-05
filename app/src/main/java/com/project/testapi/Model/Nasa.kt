package com.project.testapi.Model

class Nasa {

    var name:String?=null
    var date:String?=null
    var des:String?=null
    var title:String?=null
    var url:String?=null

    constructor(name: String?, date: String?, des: String?, title: String?, url: String?) {
        this.name = name
        this.date = date
        this.des = des
        this.title = title
        this.url = url
    }

    override fun toString(): String {
        return "Nasa(name=$name, date=$date, des=$des, title=$title, url=$url)"
    }


}