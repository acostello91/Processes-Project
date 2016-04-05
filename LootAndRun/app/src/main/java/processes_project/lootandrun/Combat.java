package processes_project.lootandrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Combat extends AppCompatActivity {

    private Character player;
    private Character zombie;
    private TextView playerTV;
    private TextView zombieTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        player  = new Character(100, "Player One",null,5,null);
        zombie  = new Character(100, "Rabid Zombie",null,15,null);

        setCombatInfo();
    }

    public void setCombatInfo()
    {
        //set player name, health and AD
        playerTV = (TextView) findViewById(R.id.playerNameView);
        playerTV.setText(player.getCharName());
        playerTV = (TextView) findViewById(R.id.playerHealthView);
        playerTV.setText("Health: "+Integer.toString(player.getHealth()));
        playerTV = (TextView) findViewById(R.id.playerADView);
        playerTV.setText("Attack Damage: "+Integer.toString(player.getAttackDamage()));

        //set Zombie name, health and AD
        zombieTV = (TextView) findViewById(R.id.zombieNameView);
        zombieTV.setText(zombie.getCharName());
        zombieTV = (TextView) findViewById(R.id.zombieHealthView);
        zombieTV.setText("Health: "+Integer.toString(zombie.getHealth()));
        zombieTV = (TextView) findViewById(R.id.zombieADView);
        zombieTV.setText("Attack Damage: " + Integer.toString(zombie.getAttackDamage()));


    }

    public void onAttackClick(View view)
    {
        int currentHP, newHP;
        currentHP = zombie.getHealth();
        newHP = currentHP - player.getAttackDamage();

        if(newHP<=0)
        {
            zombie.setDead(true);
        }

        zombie.setHealth(newHP);

        currentHP = player.getHealth();
        newHP = currentHP - zombie.getAttackDamage();

        if(newHP<=0)
        {
            player.setDead(true);
        }
        player.setHealth(newHP);



        setCombatInfo();

        if(player.isDead())
        {
            Intent intent = new Intent(this, DefeatScreen.class);
            startActivity(intent);
        }

        if(zombie.isDead())
        {
            Intent intent = new Intent(this, VictoryScreen.class);
            startActivity(intent);
        }

    }
}
