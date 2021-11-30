package com.example.mc_masterchemistry.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.DAO.InorganicaDao;
import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = InorganicaEntity.class,version = 1 ,exportSchema = false)
public abstract class TiposRoomDatabase extends RoomDatabase {

    public abstract InorganicaDao DAO();
    private static volatile TiposRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TiposRoomDatabase getRoomDataBase(final Context context) {

        if (INSTANCE==null){
            synchronized (TiposRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            TiposRoomDatabase.class,"Inorganica_1_DataBase").fallbackToDestructiveMigration().addCallback(llamada).build();
                }
            }
        }

        return INSTANCE;
    }







    private static final RoomDatabase.Callback llamada = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {

                InorganicaDao dao = INSTANCE.DAO();
                dao.deleteAll();

                InorganicaEntity tipoInorganica = new InorganicaEntity("Óxidos", R.drawable.oxidos,
                                "Existen de dos tipos:"   +"\n"+ "Combinaciones binarias de un metal con el oxígeno, en las que el oxígeno tiene número de oxidación –2. " +
                                        "\n" +
                                        "\n" +
                                        "La fórmula general de los óxidos metálicos es:\n" +
                                        "\n" + "        MO"+
                                        "\n" +
                                        "Combinaciones binarias de un no metal ( de los grupos 14, 15 y 16 )con el oxígeno, en las que el oxígeno emplea el número de oxidación -2.\n"
                                        +"\n" +
                                        "La fórmula general de los óxidos no metálicos es:\n" +
                                        "\n" + "        NO");
                dao.insert(tipoInorganica);

                tipoInorganica=new InorganicaEntity("Halogenuro de oxígeno",R.drawable.halogenuro_oxigeno,"Los Haluros de oxígeno son las combinaciones binarias de un halógeno con el oxígeno.\n"+
                        "\n"+"La fórmula general de los halogenuros de oxígeno es:\n" +
                        "\n" + "        ON"+
                        "\n" +
                        "\n" +
                        "\n" );
                dao.insert(tipoInorganica);

                tipoInorganica=new InorganicaEntity("Peróxidos",R.drawable.peroxidos,"Ciertos óxidos presentan oxígenos unidos entre si mediante un enlace simple (–O–O–), como el agua oxigenada o peróxido de hidrógeno H2O2 (H–O–O–H). El ion dióxido(2–) o ion peróxido, O22–, forma peróxidos con elementos de los grupos 1, 2, 11 y 12.\n" +
                        "El oxígeno en estos compuestos presenta número de oxidación –1."+"\n" +
                        "La fórmula general de los peróxidos es:\n" +
                        "\n" + "        M(O)"+
                        "\n" +
                        "\n" +
                        "\n" );
                dao.insert(tipoInorganica);

                tipoInorganica=new InorganicaEntity("Hidrácidos",R.drawable.hidracidos," Son combinaciones del hidrógeno con los Calcógenos (grupo 16) y los Halógenos (grupo 17).\n" +
                                "\n" +
                                "El hidrógeno actúa con número de oxidación +1, y son los únicos compuestos binarios de hidrógeno donde el hidrógeno se formula a la izquierda.    "
                       +"\n\n"+ "La fórmula general de los hidrácidos es:\n" +
                        "\n" + "        HN"+
                        "\n" +
                        "\n" +
                        "\n" );
                dao.insert(tipoInorganica);

                tipoInorganica =new InorganicaEntity("Hidruros",R.drawable.hidruros,"Existen de dos tipos:"+"\n"+"Combinaciones binarias del hidrógeno con los metales, alcalinos, alcalinoterreos y metales de transición, en las que el H tiene número de oxidación –1." +
                        "\n" +"La fórmula general de los hidruros metálicos es:" +
                        "\n" + "        MH"+
                        "\n" +
                        "\n"+ "Combinaciones binarias de un no metal ( de los grupos 13, 14 y 15 )con el hidrógeno, en las que el hidrogeno emplea el número de oxidación -1.\n"+
                        "\n" +"La fórmula general de los hidruros  no metálicos es:\n" +
                        "\n" + "        NH"+
                        "\n" +
                        "\n" +
                        "\n" );
                dao.insert(tipoInorganica);

                tipoInorganica=new InorganicaEntity("Hidróxidos",R.drawable.hidroxidos," Son compuestos ternarios que contienen un elemento metálico y tantas agrupaciones OH (hidróxido) como el número de oxidación que manifieste el metal. Con más propiedad se podrían definir como combinaciones entre cationes metálicos y aniones OH–."+
                        "\n"+"La fórmula general de los hidróxidos es:\n" +
                        "\n" + "        M(OH)"+
                        "\n" +
                        "\n" +
                        "\n" );
                dao.insert(tipoInorganica);

                tipoInorganica= new InorganicaEntity("Sal neutra binaria",R.drawable.sal_neutra_binaria,"    Son combinaciones binarias entre un metal y un no metal. Que no sea ni el oxígeno ni el hidrógeno.\n" +
                        "\n" +
                        "\n" +
                        "La fórmula general de la sal neutra binaria es:\n" +
                        "\n" + "     MN"+
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n");
                dao.insert(tipoInorganica);

                tipoInorganica=new InorganicaEntity("Oxoácidos",R.drawable.oxoacidos,"Los Oxoácidos  son compuestos poliatómicos (varios átomos) formados por el oxígeno, un elemento que suele ser no metálico y el hidrógeno.\n" +
                        "\n" +
                        "\n" +
                        "La fórmula general de los oxoácidos es:\n" +
                        "\n" +
                                "       HXO"+
                        "\n" +
                        "\n" +
                        "\n" +
                        "Donde el hidrógeno actúa con numero de oxidación +1 , mientras que el oxígeno, que actúa con el numero de oxidación -2, y el elemento no metálico (generalmente) constituyen la parte electronegativa del mismo.");
                dao.insert(tipoInorganica);


                tipoInorganica=new InorganicaEntity("Oxosales",R.drawable.oxosales," Son los derivados de sustituir todos los hidrógenos, o parte de ellos como en las sales ácidas, de los oxácidos por cationes metálicos como el Na+, o no metálicos como el NH4+ (amonio). Cuando se sustituyen todos los hidrógenos se forman las sales neutras y cuando sólo se sustituye alguno de los hidrógenos las sales ácidas.\n"+   "\n" +
                        "\n" +
                        "La fórmula general de la sal neutra binaria es:\n" +
                        "\n" + "     MNO"+
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n");
                dao.insert(tipoInorganica);


            });
        }
    };

}
