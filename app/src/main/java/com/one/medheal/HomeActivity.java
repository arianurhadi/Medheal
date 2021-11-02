package com.one.medheal;

import static android.view.Gravity.START;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.one.medheal.database.Database;
import com.one.medheal.database.Obat;
import com.one.medheal.database.ObatDao;
import com.one.medheal.drug.DrugActivity;
import com.one.medheal.drugreminder.PengingatObatActivity;
import com.one.medheal.preferences.Preferences;
import com.one.medheal.sleep.SleepActivity;
import com.one.medheal.symptom.SymptomActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private LinearLayout bottomSubMenu;
    private LinearLayout btnSymptom;
    private LinearLayout btnDrink;
    private LinearLayout btnSleep;

    private AppCompatButton btnSidebar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Date date;
    private TextView tvDate;
    private FirebaseAuth auth;
    private Database db;

    boolean visible = true;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomSubMenu = findViewById(R.id.bottomSubmenu);
        btnSymptom = findViewById(R.id.btnSymptom);
        btnDrink = findViewById(R.id.btnDrink);
        btnSleep = findViewById(R.id.btnSleep);

        tvDate = findViewById(R.id.tvTgl);

        btnSidebar = findViewById(R.id.btnSidebar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);

        auth = FirebaseAuth.getInstance();
        db = Database.getInstance(this);

        bottomNavigationView.setSelectedItemId(R.id.home);

        tvDate.setText(getDate());

        if (Preferences.getStatusLogin(HomeActivity.this)){
            navView.getMenu().getItem(0).setVisible(false);
            navView.getMenu().getItem(3).setVisible(true);
        } else {
            navView.getMenu().getItem(0).setVisible(true);
            navView.getMenu().getItem(3).setVisible(false);
        }

        //sidebar controller
        sideBar();

        //bottom navgiation controller
        bottomNav();

        if(db.obatDao().getAllObat().size() == 0){
            getInitData();
        }

    }

    @SuppressLint("WrongConstant")
    private void sideBar(){

        btnSidebar.setOnClickListener(view -> {
            drawerLayout.openDrawer(START);
        });

        navView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.login:
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    break;
                case R.id.news:
                    startActivity(new Intent(HomeActivity.this, NewsActivity.class));
                    break;
                case R.id.setting:
                    startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                    break;
                case R.id.signOut:
                    Preferences.setLogged(HomeActivity.this, false);
                    auth.signOut();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                    break;
            }
            drawerLayout.closeDrawer(START);
            return false;
        });
    }

    private void bottomNav(){
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(HomeActivity.this, HomeActivity.class);
                    intentHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentHome);
                    break;
                case R.id.search:
                    Intent intentSearch = new Intent(HomeActivity.this, SearchActivity.class);
                    intentSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentSearch);
                    break;
                case R.id.drug:
                    Intent intentDrug = new Intent(HomeActivity.this, DrugActivity.class);
                    intentDrug.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentDrug);
                    break;
                case R.id.reminder:
                    Intent intentReminder = new Intent(HomeActivity.this, PengingatObatActivity.class);
                    intentReminder.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentReminder);
                    break;
                case R.id.track:
                    if (visible){
                        bottomSubMenu.setVisibility(View.VISIBLE);
                        bottomSubMenu.animate().translationY(20);
                        visible = false;
                    } else {
                        bottomSubMenu.setVisibility(View.GONE);
                        visible = true;
                    }
                    break;
            }

            return false;
        });

        btnSymptom.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, SymptomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnDrink.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, DrinkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnSleep.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, SleepActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }

    private String getDate(){
        Locale localeIndonesia = new Locale("id", "ID");
        date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM y", localeIndonesia);

        return dateFormat.format(date);
    }

    private void getInitData(){
        ObatDao obatDao = db.obatDao();

        obatDao.insertObat(new Obat("001","OBH Combi plus Batuk Flu Menthol 100 ml", "Rp. 18.200 - Rp 32800/ per botol", "Batuk dan Flu", "ObatModel ini digunakan untuk meredakan batuk yang disertai gejala-gejala flu seperti demam, sakit kepala, hidung tersumbat, dan bersin-bersin.", "Penderita dengan gangguan jantung, diabetes melitus, gangguan fungsi hati yang berat dan hipersensitif terhadap komponen obat ini.", "Mengantuk, gangguan pencernaan, insomnia, gelisah, eksitasi, tremor, takikardia, aritmia, mulut kering.", false, R.drawable.img_obat_1));
        obatDao.insertObat(new Obat("002","ObatModel Batuk Cap Ibu & Anak Sirup 75 ml", "Rp.26.100 - Rp. 47.600 / per botol", "Batuk dan Flu", "Membantu meredakan batuk berdahak, sakit tenggorokan, dan melegakan sakit tenggorokan. Membantu memelihara kesehatan paru-paru.", "hipersensitif", "belum ada data kemanan untuk produk ini", false, R.drawable.img_obat_2));
        obatDao.insertObat(new Obat("003","Cap Lang Minyak Kayu Putih 30 ml", "Rp11.800 - Rp26.400 / per botol", "Minyak angin dan Balsam", "Membantu meringankan masuk angin, perut kembung, sakit kepala, pusing, gatal-gatal akibat gigitan serangga atau nyamuk, rasa mual", "hipersensitif", "-", false, R.drawable.img_obat_3));
        obatDao.insertObat(new Obat("004","Vicks Vaporub 10 g", "Rp8.000 - Rp15.400 / Per Pot", "Minyak angin dan Balsam", "ObatModel gosok untuk penggunaan lokal pada kulit atau untuk dihirup uapnya untuk mengurangi gejala flu dan pilek pada orang dewasa dan anak-anak", "-", "-", false, R.drawable.img_obat_4));
        obatDao.insertObat(new Obat("005","Minyak Tawon DD 30 ml", "Rp24.200 - Rp.51.300 / Per Botol", "Minyak angin dan Balsam", "Minyak gosok atau minyak urut yang dapat digunakan untuk melancarkan sirkulasi darah pada bagian yang di olesi, mencegah bengkak akibat infeksi di daerah luka.", "hipersensitif", "-", false, R.drawable.img_obat_5));
        obatDao.insertObat(new Obat("006","Hot In Cream Botol 60 ml", "Rp12.000 - Rp32.400 / Per Botol", "Minyak angin dan Balsam", "Untuk membantu meringankan pegal-pegal, nyeri sendi, nyeri otot, masuk angin, perut kembung dan sakit kepala.", "-", "-", false, R.drawable.img_obat_6));
        obatDao.insertObat(new Obat("007","Freshcare Roll On minyak Angin Green Tea 10 ml", "Rp12.000 - Rp25.600 / Per Botol", "Minyak angin dan Balsam", "Minyak ini digunakan untuk mengurangi sakit kepala, perut kembung, masuk angin, mabuk perjalanan, gejala flu, pegal pegal, gatal akibat gigitan serangga", "-", "-", false, R.drawable.img_obat_7));
        obatDao.insertObat(new Obat("008","Enervon -C 30 Tablet", "Rp 33.200 - Rp61.400 / Per Botol", "Vitamin dan Suplemen", "Suplemen vitamin untuk menjaga daya tahan tubuh serta membantu memulihkan kondisi tubuh setelah sakit", "Hipersensitif atau reaksi alergi terhadap salah satu kandungan dari Enervon-C", "Suplemen ini cenderung aman jika dikonsumsi sesuai aturan pakai atau anjuran dokter dan biasanya jarang terjadi. Namun, efek samping yang mungkin saja terjadi diantaranya: kulit memerah, mual muntah, kram perut dan sakit maag.", false, R.drawable.img_obat_8));

    }

}