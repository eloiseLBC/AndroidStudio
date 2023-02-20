package fr.projectone.salade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val buttonImage = findViewById<Button>(R.id.buttonInfo)
        val buttonImageEspace = findViewById<Button>(R.id.buttonProduits)



        // Click bouton Infos
        val buttonInfo=findViewById<Button>(R.id.buttonInfo)
        buttonInfo.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, InfosActivity::class.java)
            startActivity(intent)

        })

        // Click bouton Produits
        val buttonProduits=findViewById<Button>(R.id.buttonProduits)
        buttonProduits.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, ProduitsActivity::class.java)
            startActivity(intent)

        })
    }

    private fun setHeaderTxt(txt: String) {
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.setText(txt)
    }
}