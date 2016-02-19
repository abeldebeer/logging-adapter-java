package com.cookingfox.logging;

import com.cookingfox.logging.api.Entry;
import com.cookingfox.logging.fixture.ListenableCallLoggerAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.jcabi.matchers.RegexMatchers.containsPattern;
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

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
                assertThat(entry.getMessage(), equalTo(message));
                assertThat(level, equalTo(Level.DEBUG));
                assertThat(entry.getCaller(), equalTo(caller));
            }
        }));

        Logger.debug(message);

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_not_run_when_not_enabled() throws Exception {
        settings.setEnabled(false);

        final AtomicBoolean called = new AtomicBoolean(false);

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
            }
        }));

        Logger.debug("foo");

        assertThat(called.get(), is(false));
    }

    @Test
    public void should_format_string_using_arguments() throws Exception {
        final String foo = "foo";
        final int bar = 123;
        final String unformatted = "Message: %s (%d)";
        final String formatted = String.format(unformatted, foo, bar);
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
                assertThat(entry.getMessage(), equalTo(formatted));
            }
        }));

        Logger.debug(unformatted, foo, bar);

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_use_caller_simple_name() throws Exception {
        settings.callerUseSimpleName(true);

        final String caller = getClass().getSimpleName();
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
                assertThat(entry.getCaller(), equalTo(caller));
            }
        }));

        Logger.debug("foo");

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_add_caller_method_name() throws Exception {
        settings.callerAddMethodName(true);

        final String method = "should_add_caller_method_name";
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
                assertThat(entry.getCaller(), containsString(method));
            }
        }));

        Logger.debug("foo");

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_add_caller_line_number() throws Exception {
        settings.callerAddLineNumber(true);

        final String className = getClass().getSimpleName();
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
                assertThat(entry.getCaller(), containsPattern(className + ":\\d+"));
            }
        }));

        Logger.debug("foo");

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_not_throw_when_message_null() throws Exception {
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
                entry.getMessage();
            }
        }));

        Logger.debug(null);
        Logger.debug(null, 123, "foo");

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_output_object_toString() throws Exception {
        final AtomicBoolean called = new AtomicBoolean(false);
        final List<String> strings = Arrays.asList("a", "b", "c");
        final String toString = strings.toString();

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);

                assertThat(entry.getMessage(), equalTo(toString));
            }
        }));

        Logger.debug(strings);

        assertThat(called.get(), is(true));
    }

    @Test
    public void should_not_throw_when_no_args() throws Exception {
        final AtomicBoolean called = new AtomicBoolean(false);

        settings.setLoggerAdapter(new ListenableCallLoggerAdapter(new ListenableCallLoggerAdapter.CallListener() {
            @Override
            public void onCall(Entry entry, Level level) {
                called.set(true);
            }
        }));

        Logger.debug();

        assertThat(called.get(), is(true));
    }

}
