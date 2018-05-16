package cz.vutbr.fit.stud.xscesn00.androidcontainer;

import java.lang.reflect.*;

import android.os.*;

public class AndroidContainerAsyncTask extends AsyncTask {

  // region Private Attributes

  /**
   * Instance of object to load methods.
   */
  private Object objectInstance;
  /**
   * Method which should be invoked on resuming.
   */
  private Method resumeMethod;
  /**
   * Argument of method which should be invoked on resuming.
   */
  private String resumeMethodArgument;
  /**
   * Method which should be invoked on suspending.
   */
  private Method suspendMethod;
  /**
   * Argument of method which should be invoked on suspending.
   */
  private String suspendMethodArgument;

  /**
   * Constructor
   *
   * @param objectInstance to invoke methods.
   */
  public AndroidContainerAsyncTask(Object objectInstance) {
    this.objectInstance = objectInstance;
  }

  // endregion Private Attributes

  // region Public Setters Methods

  public void setResumeMethod(Method resumeMethod) {
    this.resumeMethod = resumeMethod;
  }

  public void setResumeMethodArgument(String resumeMethodArgument) {
    this.resumeMethodArgument = resumeMethodArgument;
  }

  public void setSuspendMethod(Method suspendMethod) {
    this.suspendMethod = suspendMethod;
  }

  public void setSuspendMethodArgument(String suspendMethodArgument) {
    this.suspendMethodArgument = suspendMethodArgument;
  }

  // endregion Public Setters Methods

  // region AsyncTask Methods

  /**
   * Overriding @AsyncTask#doInBackground() to invoke resume method.
   *
   * @param objects of null.
   *
   * @return nothing, just run investigated program.
   */
  @Override
  protected Object doInBackground(Object[] objects) {
    while (!isCancelled()) {
      try {
        this.resumeMethod.invoke(this.objectInstance, (Object) this.resumeMethodArgument);
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Overriding @AsyncTak#onCancelled() to invoke suspend method and stop execution.
   */
  @Override
  protected void onCancelled() {
    super.onCancelled();
    try {
      this.suspendMethod.invoke(this.objectInstance, (Object) this.suspendMethodArgument);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
  // endregion AsyncTask Methods
}
