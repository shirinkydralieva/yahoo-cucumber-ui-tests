# yahoo-framework-design-patterns

## Running Tests

Run tests via command line:

```bash
mvn clean test -Dbrowser=chrome -Denv=PROD -Dcucumber.filter.tags="@smoke or @regression"
```