package com.cookingfox.logging;

import com.cookingfox.logging.fixture.ListenableCallAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * JUnit tests for basic Logger functionality.
 */
public class LoggerTest {

    private Settings settings;

    @Before
    public void setUp() throws Exception {
        settings = Logger.init();
    }

    @After
    public void tearDown() throws Exception {
        settings = null;
        Logger.reset();
    }

    @Test(expected = ReflectiveOperationException.class)
    public void should_not_be_able_to_create_instance_because_singleton() throws Exception {
        Constructor constructor = Logger.class.getConstructor();
        constructor.newInstance();
    }

    @Test
    public void init_should_return_settings_instance() throws Exception {
        assertThat(settings, instanceOf(Settings.class));
    }

    @Test
    public void should_not_throw_if_not_initialized() throws Exception {
        Logger.reset();

        Logger.debug("a");
        Logger.error("b");
        Logger.info("c");
        Logger.verbose("d");
        Logger.warn("e");
    }

    @Test
    public void should_return_expected() throws Exception {
        final String message = "foo";
        final String caller = getClass().getName();
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.addAdapter(new ListenableCallAdapter(new ListenableCallAdapter.CallListener() {
            @Override
            public void onCall(ListenableCallAdapter.Call call) {
                called.set(true);
                assertThat(call.message, equalTo(message));
                assertThat(call.level, equalTo(Level.DEBUG));
                assertThat(call.caller, equalTo(caller));
            }
        }));

        Logger.debug(message);

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_not_run_when_not_enabled() throws Exception {
        settings.setEnabled(false);

        final AtomicBoolean called = new AtomicBoolean(false);

        settings.addAdapter(new ListenableCallAdapter(new ListenableCallAdapter.CallListener() {
            @Override
            public void onCall(ListenableCallAdapter.Call call) {
                called.set(true);
            }
        }));

        Logger.debug("foo");

        assertThat(called.get(), is(false));
    }

    @Test
    public void should_use_simple_class_name_when_set() throws Exception {
        settings.useSimpleClassName(true);

        final String caller = getClass().getSimpleName();
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.addAdapter(new ListenableCallAdapter(new ListenableCallAdapter.CallListener() {
            @Override
            public void onCall(ListenableCallAdapter.Call call) {
                called.set(true);
                assertThat(call.caller, equalTo(caller));
            }
        }));

        Logger.debug("foo");

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_format_string_using_arguments() throws Exception {
        final String foo = "foo";
        final int bar = 123;
        final String unformatted = "Message: %s (%d)";
        final String formatted = String.format(unformatted, foo, bar);
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.addAdapter(new ListenableCallAdapter(new ListenableCallAdapter.CallListener() {
            @Override
            public void onCall(ListenableCallAdapter.Call call) {
                called.set(true);
                assertThat(call.message, equalTo(formatted));
            }
        }));

        Logger.debug(unformatted, foo, bar);

        assertThat(called.get(), is(true));
    }

}