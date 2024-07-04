package com.sonia.recyclerviewadapter

data class Facts(
    var title:String?="",
    var description:String?=""
) {
    override fun toString(): String {
        return "$title\n $description"
    }
}

