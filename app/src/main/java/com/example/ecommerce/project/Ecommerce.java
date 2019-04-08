package com.example.ecommerce.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Ecommerce extends SQLiteOpenHelper {

    private static String Databasename = "Ecommercedatabase" ;
    SQLiteDatabase data ;
    public   Ecommerce (Context context)
    {
        super(context,Databasename ,null,10);

    }

   /* public   Ecommerce (myAdapter adapter)
    {
        super(adapter,Databasename ,null,5);

    }*/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table customers( custID integer  primary key autoincrement  , username text not null ,"+
                " password text not null ,gender text not null  ,birthdate text not null  ,question text , answer text , email text)" );

        sqLiteDatabase.execSQL(" create table orders ( orderID integer primary key autoincrement, orderDate text, orderAdress text ,cust_id integer ,foreign key (cust_id) references customers (custID) )");
        sqLiteDatabase.execSQL(" create table categories ( catID integer primary key  autoincrement, catname text ) ");
        sqLiteDatabase.execSQL(" create table products ( proID integer primary key autoincrement, proname text , price REAL , proquantity integer ,cat_id integer,foreign key (cat_id) references categories (catID) )");
        sqLiteDatabase.execSQL("create table orderdetails ( quantity integer ,order_id integer not null ,pro_id integer not null, foreign key (order_id) references orders (orderID), foreign key (pro_id) references products (proID),primary key (order_id,pro_id) )");
        sqLiteDatabase.execSQL("create table cart ( cartid integer  primary key autoincrement , customer_id integer , product_id integer ,product_name text, quantity integer ,productprice REAL )");

       insertcategories(sqLiteDatabase);
       insertproducts(sqLiteDatabase);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists customers");
        sqLiteDatabase.execSQL("drop table if exists orders");
        sqLiteDatabase.execSQL("drop table if exists categories");
        sqLiteDatabase.execSQL("drop table if exists products");
        sqLiteDatabase.execSQL("drop table if exists orderdetails");
        sqLiteDatabase.execSQL("drop table if exists cart");
        onCreate(sqLiteDatabase);

    }


    public boolean addtocart (int customerid , int productid , int quantity,String productname,float productprice)
    {

        data = getWritableDatabase() ;
        ContentValues row  = new ContentValues() ;
        row.put("customer_id" ,customerid);
        row.put("product_id" ,productid);
        row.put("product_name" ,productname);
        row.put("quantity" ,quantity);
        row.put("productprice",productprice);

        long check = data.insert("cart" ,null,row) ;
        data.close();

        if (check==-1)
            return false;
        else
            return true;
    }

    public Cursor gettocart(int userid)
    {
        data=getReadableDatabase() ;
        String[] id = {String.valueOf( userid)} ;
        String[] arg ={ "product_name" ,"quantity","product_id","cartid","productprice"} ;
        Cursor cursor = data.query("cart",arg,"customer_id like ? " ,id,null,null,null) ;

        if (cursor != null && cursor.getCount() >0 ) {
            cursor.moveToFirst();
        }
        data.close();
        return cursor ;


    }


    public void deletecart (int cart_id)
    {
        data= getWritableDatabase() ;
        data.delete("cart"," cartid='" +cart_id+ "' ",null) ;
        data.close();

    }

    public boolean updateCart (int cart_id ,int newquantity)
    {
        data = getWritableDatabase() ;
        ContentValues row = new ContentValues() ;
        row.put("quantity",newquantity);
        String [] str = {String.valueOf(cart_id)};
        int check =data.update("cart",row,"cartid like ?",str) ;
        data.close();
        if (check!=0)
        {
           return true ;
        }
        else
            return false ;
    }




    public void signup ( String username , String password , String gender , String  birthdate  ,String question ,String answer ,String email)
    {
        data = getWritableDatabase() ;
        ContentValues row = new ContentValues() ;
       // row.put("custname",name);
        row.put("username",username);
        row.put("password",password);
        row.put("gender",gender);
        row.put("birthdate",birthdate);
       // row.put("job",job);
        row.put("question",question) ;
        row.put("answer" ,answer);
        row.put("email" ,email);
        data.insert("customers",null,row) ;
        data.close();

    }

    public Cursor login (String user_name , String user_password )
    {
        data=getReadableDatabase() ;
        String[] arg = {user_name,user_password} ;

        Cursor cursor = data.rawQuery(" select  username,password,custID,question,answer from customers where username = ? and password = ?  ",arg);

        if (cursor != null )

        {

             cursor.moveToFirst() ;

        }

       // data.close();
        return cursor;
    }
   public Cursor checkusername (String user_name)
    {
        data=getReadableDatabase() ;
        String[] arg = {user_name} ;
        Cursor cursor = data.rawQuery(" select username from customers where username = ?  ",arg);
        if (cursor != null)

        {
            cursor.moveToFirst() ;

        }

        data.close();
        return cursor;

    }

    public  Cursor forgetpassword (String user_name , String ques,String ans)


    {
        boolean bool ;
        data=getReadableDatabase() ;
        String[] arg = {user_name,ques,ans} ;
        Cursor cursor = data.rawQuery(" select password, answer ,custID from customers where username = ? and question = ? and answer = ?  ",arg);

        if (cursor != null && cursor.getCount() > 0)

        {
            cursor.moveToFirst() ;

            bool = true ;
        }
        else
            {

            bool = false ;
        }

        data.close();
     return cursor ;
    }

    public Cursor fetchallemployees (String name)
    {
       data= getReadableDatabase() ;
       String[] rowdeatils = {'%' + name + '%'};
       Cursor cursor = data.rawQuery("select custname from customers where lower (custname)  like ? ",rowdeatils);
       if(cursor !=null)
       {
           cursor.moveToFirst() ;
       }
       data.close();
       return cursor ;

    }

 public void insertcategories (SQLiteDatabase sqLiteDatabase)
    {

        //data = getWritableDatabase() ;
        ContentValues row = new ContentValues() ;
        //row.put("catID",1);
        row.put("catname","devices");
        sqLiteDatabase.insert("categories",null,row) ;

        //row.put("catID",2);
        row.put("catname","clothes");
        sqLiteDatabase.insert("categories",null,row) ;

        //row.put("catID",3);
        row.put("catname","books");
        sqLiteDatabase.insert("categories",null,row) ;


        //data.close();


    }
 public  Cursor fetchallcateories ()
     {
            data=getReadableDatabase() ;
            String [] arg ={"catID","catname"};
          Cursor cursor =  data.query("categories",arg,null,null,null,null,null) ;

         if (cursor != null && cursor.getCount() >0 ) {
             cursor.moveToFirst();
         }
         data.close();
        return cursor ;

     }

    public void insertproducts (SQLiteDatabase sqLiteDatabase)
    {

        //data = getWritableDatabase() ;
        ContentValues row = new ContentValues() ;
        row.put("proname","mobile");
        row.put("price",20);
        row.put("proquantity",10);
        row.put("cat_id",1);
        sqLiteDatabase.insert("products",null,row) ;

        row.put("proname","lap");
        row.put("price",5000);
        row.put("proquantity",5);
        row.put("cat_id",1);
        sqLiteDatabase.insert("products",null,row) ;

        row.put("proname","wireless mouse");
        row.put("price",60.5);
        row.put("proquantity",2);
        row.put("cat_id",1);
        sqLiteDatabase.insert("products",null,row) ;

        row.put("proname","keyboard");
        row.put("price",20);
        row.put("proquantity",10);
        row.put("cat_id",2);
        sqLiteDatabase.insert("products",null,row) ;

        row.put("proname","LCD");
        row.put("price",20);
        row.put("proquantity",10);
        row.put("cat_id",2);
        sqLiteDatabase.insert("products",null,row) ;
    }

    public Cursor getproducts (int catId)
    {
        data = getReadableDatabase() ;
        String[] arg = {String.valueOf(catId)} ;
        Cursor cursor = data.rawQuery("select proname,price,proquantity,proID from products where cat_id like ? ",arg) ;

        if (cursor != null && cursor.getCount() >0 )
        {
            cursor.moveToFirst();
        }

        data.close();
        return cursor ;

    }
    public Cursor getProductsDetails (int proId)
    {
        data = getReadableDatabase() ;
        String[] arg = {String.valueOf(proId)} ;
        Cursor cursor = data.rawQuery("select proname,price,proquantity from products where proID like ? ",arg) ;

        if (cursor != null && cursor.getCount() >0 )
        {
            cursor.moveToFirst();
        }

        data.close();
        return cursor ;

    }

    public Cursor getallproducts (String proname)
    {
        data = getReadableDatabase() ;
        String[] arg = {'%' + proname + '%'}; ;
        Cursor cursor = data.rawQuery("select proname,price,proquantity,proID from products where lower (proname) like ? ",arg) ;

        if (cursor != null && cursor.getCount() >0 )
        {
            cursor.moveToFirst();
        }

        data.close();
        return cursor ;

    }


    public Cursor getallcategories (String catname)

    {
        data= getReadableDatabase() ;
        String [] arg = {'%' + catname + '%'}; ;
        Cursor cursor = data.rawQuery(" select catname from categories where lower (catname) like ?" , arg) ;
        if (cursor != null && cursor.getCount() >0 )
        {
            cursor.moveToFirst();
        }

        data.close();
        return cursor ;

    }

 public Cursor  getuseremail (int id )
 {

     data= getReadableDatabase() ;
     String [] arg = {String.valueOf(id)} ;
     Cursor cursor = data.rawQuery(" select  email from customers where custID = ? ",arg);

     if (cursor != null )

     {

         cursor.moveToFirst() ;

     }

      data.close();
     return cursor;

 }



}
