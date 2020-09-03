package com.example.mc_masterchemistry.db;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.DAO.ElementoDao;
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = ElementoEntity.class,version = 2 ,exportSchema = false)
public abstract class ElementoRoomDatabase extends RoomDatabase {

    public abstract ElementoDao DAO();
    private static volatile ElementoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    private static final String IM1 =
            "ALTER TABLE Elementos ADD IM1 INTEGER NOT NULL DEFAULT ''";
    private static final String IM2 =
            "ALTER TABLE Elementos ADD IM2 INTEGER NOT NULL  DEFAULT ''";
    private static final String IM3 =
            "ALTER TABLE Elementos ADD IM3 INTEGER NOT NULL  DEFAULT ''";
    private static final String IM4 =
            "ALTER TABLE Elementos ADD IM4 INTEGER NOT NULL  DEFAULT ''";
    private static final String simbolo =
            "ALTER TABLE Elementos ADD simbolo TEXT";





    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    public static ElementoRoomDatabase getRoomDataBase(final Context context) {

        if (INSTANCE==null){
            synchronized (ElementoRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            ElementoRoomDatabase.class,"Elemento_1_DataBase").addCallback(llamada).addMigrations(migrate).build();
                }
            }
        }

        return INSTANCE;
    }
    static final Migration migrate = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(IM1);
            database.execSQL(IM2);
            database.execSQL(IM3);
            database.execSQL(IM4);
            database.execSQL(simbolo);
        }
    };


    private static RoomDatabase.Callback llamada = new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {

                        super.onCreate(db);

                        databaseWriteExecutor.execute(() -> {

                            ElementoDao dao = INSTANCE.DAO();
                            dao.deleteAll();

                ElementoEntity elemento = new ElementoEntity("Hierro",0,2, 3,0,0, R.drawable.hierro_2, R.drawable.hierro_3,0,0 ,"Fe" );
                dao.insert(elemento);
                elemento = new ElementoEntity("Cobalto", 1,2, 3,0,0, R.drawable.cobalto_2, R.drawable.cobalto_3,0,0,"Co");
                dao.insert(elemento);
                elemento = new ElementoEntity("Niquel", 2,2, 3,0,0,R.drawable.niquel_2,R.drawable.niquel_3,0,0,"Ni");
                dao.insert(elemento);
                elemento = new ElementoEntity("Cobre", 3,1, 2,0,0,R.drawable.cobre_1,R.drawable.cobre_2,0,0, "Cu");
                dao.insert(elemento);
                elemento = new ElementoEntity("Paladio", 4,2, 4,0,0,R.drawable.paladio_2,R.drawable.paladio_4,0,0,"Pd");
                dao.insert(elemento);
                elemento = new ElementoEntity("Platino", 5,2, 4,0,0,R.drawable.platino_2,R.drawable.platino_4,0,0,"Pt");
                dao.insert(elemento);
                elemento = new ElementoEntity("Oro",6, 1, 3,0,0,R.drawable.oro_1,R.drawable.oro_3,0,0,"Au");
                dao.insert(elemento);
                elemento = new ElementoEntity("Mercurio", 7,1, 2,0,0,R.drawable.mercurio_1,R.drawable.mercurio_2,0,0,"Hg");
                dao.insert(elemento);
                elemento = new ElementoEntity("Talio", 8,1, 3,0,0,R.drawable.talio_1, R.drawable.talio_3,0,0,"Tl");
                dao.insert(elemento);
                elemento = new ElementoEntity("Estaño", 9,2, 4,0,0,R.drawable.estanno_2,R.drawable.estanno_4,0,0,"Sn");
                dao.insert(elemento);
                            //No metal
                elemento = new ElementoEntity("Antimonio", 10,-3, 3,5,0,R.drawable.antimonio_3_neg,R.drawable.antimonio_3,R.drawable.antimonio_5,0,"Sb");
                dao.insert(elemento);
                elemento = new ElementoEntity("Arsenico", 11,-3, 3,5,0,R.drawable.arsenico_3_neg,R.drawable.arsenico_3,R.drawable.arsenico_5,0,"As");
                dao.insert(elemento);
                elemento = new ElementoEntity("Fosforo", 12,-3, 3,4,5,R.drawable.fosforo_3_neg,R.drawable.fosforo_3,R.drawable.fosforo_4,R.drawable.fosforo_5,"P");
                dao.insert(elemento);
                elemento = new ElementoEntity("Hidrogeno", 13,-1, 1,0,0,R.drawable.hidrogeno_1_neg,R.drawable.hidrogeno_1,0,0,"H");
                dao.insert(elemento);
                elemento = new ElementoEntity("Teluro", 14,-2, 4,6,0,R.drawable.teluro_2_neg,R.drawable.teluro_4,R.drawable.teluro_6,0,"Te");
                dao.insert(elemento);
                elemento = new ElementoEntity("Selenio", 15,-2, 4,6,0,R.drawable.selenio_2_neg,R.drawable.selenio_4,R.drawable.selenio_6,0,"Se");
                dao.insert(elemento);
                elemento = new ElementoEntity("Azufre", 16,-2, 2,4,6,R.drawable.azufre_2_neg,R.drawable.azufre_2,R.drawable.azufre_4,R.drawable.azufre_6,"S");
                dao.insert(elemento);
                elemento = new ElementoEntity("Oxígeno", 17,-2, -2,0,0,R.drawable.oxigeno_2_neg,R.drawable.oxigeno_2_neg,0,0,"O");
                dao.insert(elemento);
                elemento = new ElementoEntity("Astato", 18,-1, 1,3,5,R.drawable.astato_1_neg,R.drawable.astato_1,R.drawable.astato_3,R.drawable.astato_5,"At");
                dao.insert(elemento);
                elemento = new ElementoEntity("Iodo",19, -1, 1,3,5,R.drawable.yodo_1_neg,R.drawable.yodo_1,R.drawable.yodo_3,R.drawable.yodo_5,"I");
                dao.insert(elemento);
                elemento = new ElementoEntity("Bromo", 20,-1, 1,3,5,R.drawable.bromo_1_neg,R.drawable.bromo_1,R.drawable.bromo_3,R.drawable.bromo_5,"Br");
                dao.insert(elemento);
                elemento = new ElementoEntity("Cloro", 21,-1, 1,3,5,R.drawable.cloro_1_neg,R.drawable.cloro_1,R.drawable.cloro_3,R.drawable.cloro_5,"Cl");
                dao.insert(elemento);


            });
        }

    };




}
