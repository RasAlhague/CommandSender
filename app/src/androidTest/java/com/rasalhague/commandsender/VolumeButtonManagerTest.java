package com.rasalhague.commandsender;

import android.test.AndroidTestCase;
import com.rasalhague.commandsender.volumebtncontrol.VolumeButtonManager;

public class VolumeButtonManagerTest extends AndroidTestCase
{
    private VolumeButtonManager volumeButtonManager;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();

        volumeButtonManager = new VolumeButtonManager(getContext());
    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testVolumeButtonEvents() throws Exception
    {

    }
}
