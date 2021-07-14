package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AlarmTests {
    Sensor mockSensor;
    Alarm alarm;

    @Before
    public void initializeObjects() {
        this.mockSensor = Mockito.mock(Sensor.class);
        this.alarm = new Alarm(mockSensor);

    }

    @Test
    public void testIfAlarmGoesOffHighPressure() {
        Mockito.when(mockSensor.popNextPressurePsiValue()).thenReturn(100.12);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testIfAlarmGoesOffLowPressure() {
        Mockito.when(mockSensor.popNextPressurePsiValue()).thenReturn(-12.4);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testIfAlarmDoesNotGoOffNormalPressure() {
        Mockito.when(mockSensor.popNextPressurePsiValue()).thenReturn(18.2);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }
}
