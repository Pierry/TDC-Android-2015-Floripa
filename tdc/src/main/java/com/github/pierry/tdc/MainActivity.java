package com.github.pierry.tdc;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.rengwuxian.materialedittext.MaterialEditText;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

// Criando o OptionMenu e iniciando a Activity
@OptionsMenu(R.menu.menu_main) @EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

  // Instanciando as views
  @ViewById TextView tvTitulo;
  @ViewById TextView tvProgresso;
  @ViewById Button btnTitulo;
  @ViewById Button btnProgresso;
  @ViewById MaterialEditText etExemplo;

  @Bean(UserRepository.class) IUserRepository userRepository;

  private boolean alterado = true;
  private static final String TITULO_PADRAO = "Titulo Padrão";
  private static final String TITULO_ALTERADO = "Titulo Alterado";

  // Executa o metódo após instanciar as views
  @AfterViews void alterarActionBar() {
    getSupportActionBar().setTitle("Android Annotations");
  }

  @Click void btnTitulo() {
    alterarTitulo();
  }

  @Click void btnProgresso() {
    Toast.makeText(this, userRepository.create(), Toast.LENGTH_SHORT).show();
    alterarProgresso();
  }

  // Atualiza a view tvTitulo (Necessário para atualizar views)
  @UiThread void alterarTitulo() {
    if (alterado) {
      tvTitulo.setText(TITULO_PADRAO);
    } else {
      tvTitulo.setText(TITULO_ALTERADO);
    }
    alterado = !alterado;
  }

  @Click void btnAdicionar() {
    ProgressDialog dialog = ProgressDialog.
        show(this, "", "Adicionando...");
    adicionarItems(dialog);
  }

  @Background void adicionarItems(ProgressDialog dialog) {
    // Executa as ações
    dialog.dismiss();
  }

  // Executa tarefas em background
  @Background void alterarProgresso() {
    for (int i = 0; i <= 100000; i++) {
      // Atualiza a view com o percentual carregado
      atualizarTvProgresso(i / 1000);
    }
  }

  // Atualiza o contador
  @UiThread void atualizarTvProgresso(int valor) {
    tvProgresso.setText(String.valueOf(valor) + "%");
  }

  // Click do m enu settings
  @OptionsItem(R.id.action_settings) void btnActionSettings() {
    Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show();
  }
}
