package com.mobitant.mysqlitecrudmodule

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DBHandler (context: Context, name :String?, factory: SQLiteDatabase.CursorFactory?, version : Int ) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    //싱글톤 패턴이다. 객체를 한번만 생성한 후 재활용한다.
    companion object {
        private val DATABASE_NAME = "MyData.db"
        private val DATABASE_VERSION = 2

        val CUSTOMERS_TABLE_NAME = "Customers"
        val COLUMN_CUSTOMERID = "customerid"
        val COLUMN_CUSTOMERNAME = "customername"
        val COLUMN_MAXCREDIT = "maxcredit"
        val COLUMN_PHONENO = "phoneno"
    }

    //DB에 Customers이름의 테이블을 만든다.
    //칼럼명 culstomerid 자료형 integer 기본키 자동으로 1이 증가하는 값을 가진다.
    //칼럼명 customername 자료형 text
    //칼럼명 maxcredit 자료형 double 초기값 0
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CUSTOMERS_TABLE = ("CREATE TABLE $CUSTOMERS_TABLE_NAME (" +
                "$COLUMN_CUSTOMERID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CUSTOMERNAME TEXT," +
                "$COLUMN_MAXCREDIT DOUBLE DEFAULT 0)")
        db?.execSQL(CREATE_CUSTOMERS_TABLE)

    }

    //버젼이 2가 되면 칼럼 하나를 더 추가한다.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion<2){
            db?.execSQL("Alter Table $CUSTOMERS_TABLE_NAME " +
            "Add $COLUMN_PHONENO TEXT NULL")
        }
    }

    //DB에 저장된 데이터를 가져온다.
    fun getCustomers(mCtx : Context) : ArrayList<Customer>{
        val qry = "Select * From $CUSTOMERS_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        // DB에 저장된 데이터를 가져와 Customer() 데이터 클래스에 저장한다.
        if(cursor.count == 0)
            Toast.makeText(mCtx,"No Records Found", Toast.LENGTH_SHORT).show()

        else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                val customer = Customer()
                customer.customerID = cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMERID))
                customer.customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERNAME))
                customer.maxCredit = cursor.getDouble(cursor.getColumnIndex(COLUMN_MAXCREDIT))
                customer.phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONENO))
                customers.add(customer)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx,"${cursor.count} Records Found", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return customers
    }

    //DB에 데이터를 입력한다.
    //Customer에 있는 데이터 클래스의 정보를 가져와 DB에 저장한다.
    fun addCustomer(mCtx: Context, customer: Customer){
        val values = ContentValues()
        values.put(COLUMN_CUSTOMERNAME, customer.customerName)
        values.put(COLUMN_MAXCREDIT,customer.maxCredit)
        values.put(COLUMN_PHONENO, customer.phoneNumber)
        val db = this.writableDatabase
        try {
            db.insert(CUSTOMERS_TABLE_NAME,null,values)
            Toast.makeText(mCtx,"Customer Added",Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(mCtx,e.message,Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    //칼럼에 있는 ID값과 DB의 id값이 같다면 DB의 데이터를 삭제한다.
    fun deleteCustomer(customerID: Int): Boolean {
        val qry = "Delete From $CUSTOMERS_TABLE_NAME where $COLUMN_CUSTOMERID = $customerID"
        val db = this.writableDatabase
        var result =false
        try {
            val cursor = db.execSQL(qry)
            result = true
        } catch (e : Exception){
            Log.e(ContentValues.TAG, "Error Deleting")
        }
        db.close()
        return result
    }

    //이름이 같다면 데이터를 업데이트 한다.
    fun updateCustomer(id: String, customerName : String, maxCredit : String, phoneNumber : String) : Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result = false
        contentValues.put(COLUMN_CUSTOMERNAME, customerName)
        contentValues.put(COLUMN_MAXCREDIT,maxCredit.toDouble())
        contentValues.put(COLUMN_PHONENO,phoneNumber)
        try {
            db.update(CUSTOMERS_TABLE_NAME,contentValues,"$COLUMN_CUSTOMERNAME = ?",arrayOf(id))
            result = true
        }catch (e:Exception){
            Log.e(ContentValues.TAG, "Error Updating")
            result = false
        }
        return result
    }

}