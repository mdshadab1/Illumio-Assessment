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
### Test Coverage
The application includes comprehensive unit and integration tests covering the following areas:

#### Flow Log Parsing:

- Correct parsing of valid log entries
- Handling of invalid or malformed log entries
- Edge cases (empty lines, extra whitespace)


#### Lookup Table Loading:

- Correct loading of valid lookup entries
- Handling of invalid lookup table format
- Case insensitivity in protocol matching


#### Tag Matching:

- Correct matching of flow log entries to tags
- Handling of unmatched entries (should be tagged as "Untagged")
- Performance testing with large datasets


#### Output Generation:

- Correct counting of tags and port/protocol combinations
- Proper CSV formatting of output
- Handling of edge cases (no matches, all untagged)


#### Integration Tests:

- End-to-end testing of the entire flow from input to output
- Verification of correct output for known input datasets

## Analysis and Considerations

### Scalability:

- The application uses efficient data structures (HashMaps) for lookup and counting, ensuring O(1) average time complexity for tag matching and counting operations.
- For very large datasets (billions of entries), consider implementing batch processing or using distributed computing frameworks.


### Memory Usage:

- The program processes log entries one at a time, keeping memory usage constant regardless of input size.
- Lookup table is kept in memory for fast access, which is efficient for tables up to millions of entries.


### Extensibility:

- The modular design with clear interfaces (Parser, LookupLoader, TagMatcher, etc.) allows for easy extension or modification of individual components.
- New tag matching strategies can be implemented by creating new classes that implement the TagMatchStrategy interface.


### Error Handling and Logging:

- Comprehensive error handling is implemented to gracefully handle and log various error scenarios (parsing errors, invalid logs, etc.).
- Logging is used throughout the application for easier debugging and monitoring.


### Performance Optimizations:

- StringBuilder is used for efficient string concatenation in output generation.
- Efficient use of hash-based data structures (HashMap) for quick lookups and counting.
- Stream processing of log entries to handle large files without loading everything into memory.
- Use of primitive types where possible to reduce object creation overhead.
- Minimizing string manipulations in tight loops to reduce garbage collection pressure.


### Potential Improvements:

- Implement multi-threading for parsing and processing large log files.
- Add support for compressed input files to handle very large datasets more efficiently.
- Implement a more sophisticated tag matching algorithm for complex rule sets.
- Consider caching frequently used strings, such as protocol names, to reduce string creation overhead.
- Implement lazy loading or pagination for very large lookup tables.
- Use memory-mapped files for processing extremely large log files more efficiently.

## Conclusion

This Flow Log Parser efficiently processes flow logs and generates detailed reports, fulfilling the specified requirements. Built on Spring Boot, it offers a robust, extensible solution for flow log analysis.
