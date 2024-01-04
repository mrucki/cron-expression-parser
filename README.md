# Cron Expression Parser Command-Line App

This is a simple Java command-line application that parses cron expression and prints it in formatted table.

## Requirements

- OS X/Linux terminal
- git
- Java 17 (not tested with other versions - try if you are brave)

## Usage

1. Clone the repository:

```bash
git clone https://github.com/mrucki/cron-expression-parser.git
```

2. Build and run the application:

```bash
  cd cron-expression-parser

  #if you run it for the first time
  chmod +x run.sh


  ./run.sh cronExpression
```

Replace `cronExpression` with the correct cron expression.

## Examples
```bash
./run.sh "*/3 0,15,35 0,1,15,31,90 * 1-2 /usr/bin/find"
```