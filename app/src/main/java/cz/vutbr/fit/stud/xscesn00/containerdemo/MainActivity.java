package cz.vutbr.fit.stud.xscesn00.containerdemo;

import android.os.*;

import android.support.v7.app.*;

import timber.log.*;

import cz.vutbr.fit.stud.xscesn00.androidcontainer.*;

public class MainActivity extends AppCompatActivity {

  private AndroidContainer container;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Timber.d("onCreate()");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    this.container = new AndroidContainer();
  }

  @Override
  protected void onResume() {
    super.onResume();

    this.container.onContainerResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    this.container.onContainerSuspend();
  }
}
