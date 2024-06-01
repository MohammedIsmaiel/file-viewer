# File System Explorer

## It was a sub feature of a big app

### this side focus on reading file system, viewing and tailing files

This project is a Spring Boot application that allows users to explore the file system. It provides APIs to list the contents of directories, view files, and tail files in real-time. The application is designed to sort directories first, followed by files, and includes metadata for each file or directory.

## Features

- List contents of a directory with metadata
- Sort directories and files (directories first, then files)
- Metadata includes:
  - Name
  - Is Directory
  - File Extension (for files)
  - Size
  - Last Modified Date
- View file content (upcoming feature)
- Tail file content (upcoming feature)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MohammedIsmaiel/file-viewer.git
   cd file-system-explorer
   ```
