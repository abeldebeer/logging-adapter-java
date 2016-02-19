# Logging Adapters for Java: Change Log

## [0.4.0](../../tree/v0.4.0) (2016-02-19)

- Removes support for multiple logger adapters: use `Settings.setLoggerAdapter(adapter)`.

## [0.3.2](../../tree/v0.3.2) (2016-02-04)

- Adds support for empty log, e.g.: `Logger.debug()`
- Adds support for Object (non-String) log, e.g.: `Logger.debug(myObject)`

## [0.3.1](../../tree/v0.3.1) (2015-09-17)

- Fixes weird formatting in Android logcat when message is empty.
- Fixes exception when message is null.

## [0.3.0](../../tree/v0.3.0) (2015-09-03)

- Simplifies caller options.

## [0.2.2](../../tree/v0.2.2) (2015-09-02)

- Removes Thread id from SystemOutLoggerAdapter.

## [0.2.1](../../tree/v0.2.1) (2015-09-02)

- Added support for file information (name, line number) for the calling object.
- More flexible logging using `Entry` interface.

## [0.1.2](../../tree/v0.1.2) (2015-09-01)

- Implements setting to include caller method name in log message.

## [0.1.0](../../tree/v0.1.0) (2015-09-01)

- First version with basic functionality.
