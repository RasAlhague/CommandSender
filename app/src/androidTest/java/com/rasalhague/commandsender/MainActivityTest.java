package com.rasalhague.commandsender;

import android.os.StrictMode;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import com.rasalhague.commandsender.gui.IPEditText;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private MainActivity mainActivity;
    private EditText     editText_IP;
    private IPEditText   ipEditText;

    public MainActivityTest()
    {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception
    {
        super.setUp();

        mainActivity = getActivity();
        editText_IP = (EditText) mainActivity.findViewById(R.id.editText_IP);
        ipEditText = new IPEditText(editText_IP);
    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testPreconditions()
    {
        assertNotNull("mainActivity is null", mainActivity);
        assertNotNull("editText_IP is null", editText_IP);
    }

    public void testEditText_IP() throws Throwable
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        runTestOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                editText_IP.setText("192.168.1.1022"); //wrong
            }
        });
        assertFalse(editText_IP.getText().toString().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));

        runTestOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                editText_IP.setText("192.168.1.102"); //correct, but no connection
            }
        });
        assertTrue(editText_IP.getText().toString().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));

        runTestOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                editText_IP.setText("192.168.1.10");
            }
        });
        assertTrue(editText_IP.getText().toString().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
    }
}
