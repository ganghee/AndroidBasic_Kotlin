package com.mobitant.myrealmmodule

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//RealmObject를 상속받는다.
//@PrimaryKey는 기본키를 의미한다. id는 자동으로 하나씩 증가한다.
//자동으로 하나씩 증가하는 코드는 Realm객체가 지원하지 않으므로 직접 구현해야 한다.
open class Todo (
    @PrimaryKey var id:Long = 0,
    var title:String = "",
    var date:Long = 0
): RealmObject() {}