package ph.kodego.alfaro.vismarjay.module_2.model

import ph.kodego.alfaro.vismarjay.module_2.R

class Student (var firstName:String="Unknown", var lastName:String = "Unknown", var img:Int){

    var id: Int = 0
    var yearstarted : Int = 0
    var course = ""
    constructor(): this("","", R.drawable.profileholder){

    }
}