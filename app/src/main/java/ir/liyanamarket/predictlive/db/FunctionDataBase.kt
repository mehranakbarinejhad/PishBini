package ir.liyanamarket.predictlive.db

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteException
import ir.liyanamarket.predictlive.dataclass.LoginUserModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FunctionDataBase :KoinComponent{
    private val createDataBase:CreateDataBase by inject()
    @SuppressLint("Recycle")
    private fun validateUser(username: String):Boolean
    {

        val db=createDataBase.readableDatabase
        val cursor=db.rawQuery("select * from tbl_users where username='$username'",null)
        return cursor.count>0
    }
    fun insertuser(username:String,name:String,password:String,image:String)
    {
        val db=createDataBase.writableDatabase
        if(username.isNotEmpty()&&password.isNotEmpty()) {


            if(!validateUser(username))
             {
                 db.execSQL("insert into tbl_users(username,name,password,image)values('$username','$name','$password','$image')")
             } else
        {

            db.execSQL("update tbl_users set username='$username',name='$name',password='$password',image='$image' where username='$username'")
        }
        }


    }
    @SuppressLint("Recycle")
    fun selectuser():LoginUserModel
    {
        var username=""
        var name=""
        var password=""
        var image=""
        val db=createDataBase.readableDatabase
        val cursor=db.rawQuery("select * from tbl_users",null)
       try {
           cursor.moveToLast()
            username = cursor.getString(1)
           name=cursor.getString(2)
            password = cursor.getString(3)
            image = cursor.getString(4)

       }
       catch (ex:SQLiteException){}
        return LoginUserModel(username,name,password, image)
    }


}