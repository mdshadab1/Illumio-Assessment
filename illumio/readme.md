# Illumio Assessment - Flow Log Parser

## Introduction

The Flow Log Parser is a Java Spring Boot application designed to analyze and categorize network flow logs. It reads a flow log file, maps specific port and protocol combinations to predefined tags using a lookup table, and generates a detailed output report in CSV format.

## Assumptions and Limitations

1. **Supported Log Format:** 
   - The program supports the AWS VPC flow log format version 2.
   - Custom log formats are not supported.
   
2. **Protocol Identification:** 
   - The tool maps protocol numbers (e.g., 6 for TCP, 17 for UDP, 1 for ICMP) to their respective names.
   - Any unrecognized protocol numbers are categorized as "unknown".
   
3. **Case Insensitivity:** 
   - The matching of protocols in the lookup table and flow log is case insensitive.
   
4. **Handling Unmatched Entries:** 
   - Flow log entries that do not match any tag in the lookup table are classified as "Untagged."

5. **Output File:** 
   - The program generates a single CSV file containing both tag counts and port/protocol combination counts.

## Project Structure

- **src/main/java/com/illumio/**: Contains the main Java classes.
- **src/main/resources/**: Contains application properties and input files.
- **src/test/**: Contains test classes for the application.

## How to Run the Program

### Prerequisites

- Java 11
- Maven
  
Make sure that the Maven is using Java 11.
Steps to make Maven use Java 11. Run on your Terminal
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.x.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
```
To make these changes permanent, you need to add these lines to your shell configuration file. This is typically ~/.bash_profile, ~/.zshrc, or ~/.profile depending on your shell and OS.
```
nano ~/.bash_profile
```

### Building the Application

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/Illumio-Assessment.git
   cd Illumio-Assessment
   ```

2. Build the project using Maven:
   ```
   mvn clean package
   ```

### Running the Application

1. After building, run the application using:
   ```
   java -jar target/illumio.jar
   ```

2. Alternatively, you can use Maven to run the application:
   ```
   mvn spring-boot:run
   ```

By default, the application will use the input and output file paths specified in the `application.properties` file.

## Input and Output

- Input files (flow log and lookup table) should be placed in the `data/input/` directory.
- The output CSV file will be generated in the location specified in `application.properties`.

I have included the data directory in the git, you can check data/output/output.csv for the result.

## Configuration

You can modify the `src/main/resources/application.properties` file to change input and output file paths:

```properties
app.input.flow-log-file=path/to/your/flow_logs.txt
app.input.lookup-file=path/to/your/lookup_table.csv
app.output.file=path/to/your/output.csv
```

## Testing

Run the tests using:
```
mvn test
```

## Conclusion

This Flow Log Parser efficiently processes flow logs and generates detailed reports, fulfilling the specified requirements. Built on Spring Boot, it offers a robust, extensible solution for flow log analysis.
