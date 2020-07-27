package ir.adromsh.digikala.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.Model.Profile;
import ir.adromsh.digikala.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String EDIT_PROFILE = "ویرایش اطلاعات";
    private static final String ORDER_PROFILE = "سفارشات من";
    private static final String FAV_PROFILE = "لیست مورد علاقه";
    private static final String MESSAGE_PROFILE = "پیام ها";
    private static final String ADDRESS_PROFILE = "آدرس های من";
    private static final String CREDIT_PROFILE = "اطلاعات کارت";
    private static final String EXIT_PROFILE = "خروج";
    String email;
    RecyclerView recyclerView;
    TextView txtEmail;

    List<Profile> profiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupViews();
        email = getIntent().getExtras().getString("email");
        loadProfileList();
        recyclerView.setAdapter(new ProfileAdapter(ProfileActivity.this,profiles, new ProfileAdapter.OnProfileItemClick() {
            @Override
            public void onClick(String title) {
                switch (title) {
                    case EDIT_PROFILE:
                        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                        break;
                    case FAV_PROFILE:
                        Intent favIntent=new Intent(ProfileActivity.this,FavActivity.class);
                        favIntent.putExtra("email",email);
                        startActivity(favIntent);

                }
            }
        }));

    }

    private void loadProfileList() {
        Profile edtProfile = new Profile();
        edtProfile.setTitle(EDIT_PROFILE);
        edtProfile.setIcon(R.drawable.ic_edit_black_24dp);
        profiles.add(edtProfile);

        Profile orderProfile = new Profile();
        orderProfile.setTitle(ORDER_PROFILE);
        orderProfile.setIcon(R.drawable.ic_orders_black_24dp);
        profiles.add(orderProfile);

        Profile favProfile = new Profile();
        favProfile.setTitle(FAV_PROFILE);
        favProfile.setIcon(R.drawable.ic_favorite_black_24dp);
        profiles.add(favProfile);

        Profile messageProfile = new Profile();
        messageProfile.setTitle(MESSAGE_PROFILE);
        messageProfile.setIcon(R.drawable.ic_message_black_24dp);
        profiles.add(messageProfile);

        Profile addressProfile = new Profile();
        addressProfile.setTitle(ADDRESS_PROFILE);
        addressProfile.setIcon(R.drawable.ic_edit_black_24dp);
        profiles.add(addressProfile);

        Profile cardProfile = new Profile();
        cardProfile.setTitle(CREDIT_PROFILE);
        cardProfile.setIcon(R.drawable.ic_credit_card_black_24dp);
        profiles.add(cardProfile);

        Profile exitProfile = new Profile();
        exitProfile.setTitle(EXIT_PROFILE);
        exitProfile.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        profiles.add(exitProfile);


    }

    private void setupViews() {
        recyclerView = findViewById(R.id.rv_profile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtEmail = findViewById(R.id.txt_profile_email);
        txtEmail.setText(getIntent().getExtras().getString("email"));
    }
}
