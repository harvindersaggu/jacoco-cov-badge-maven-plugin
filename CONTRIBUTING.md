### Contents
- [Project Setup](#project-setup)
- [Building the Project](#building-the-project)
- [Technical Overview](#technical-overview)
  - [Base Idea](#base-idea)
  - [Code overview](#code-overview)
  - [Dependencies](#dependencies)
- [Branching Conventions](#branching-conventions)
- [Commit Message Conventions](#commit-message-conventions)

## Project Setup
* `maven` version: 3.5.2
* `java` version: Oracle JDK 1.8

Make sure you are using correct `maven` version and required paths are added to classpath.  
The project setup is quite simple. It's a standard `maven` project.
You can import the project into any ide such as `Eclipse` or `IntelliJ IDEA`.

`maven` will take care of resolving dependencies.
Both these IDE support importing of `maven` project directly.  
Refer to their documentation to know more on how to import `maven` project in it.

## Building the Project
The project can be built in multiple ways. Several IDE's directly support `maven` support natively.
That can be leveraged directly to execute the targets. Few of the important `maven` targets are:
* `clean`: Clean up the build i.e. delete `target` directory from project.
* `package`: Generates the plugin `jar`, which can be included as part of dependencies in your maven
project by explicitly specifying dependency path to a local `jar`.  
Refer `maven` documentation to know how to add local jar as dependency.
* `install`: Generates the plugin `jar` and install it in your local repository. Generally local repository
resides in `$HOME\.m2` directory. This can then be directly included as the dependency in your project.

To build project from command line you can use these commands as per your needs.
*project-dir* is the location where `pom.xml` is present.
```console
foo@bar:~/poject-dir $  mvn clean            # To clean up target directory
foo@bar:~/poject-dir $  mvn package          # To generate jar
foo@bar:~/poject-dir $  mvn install          # To generate jar and install it local maven repo
foo@bar:~/poject-dir $  mvn clean install    # Recommanded way to do a full build
```


## Technical Overview
This section comprises of a brief on technical aspects of the project such as the coding
structure, base idea and dependencies used to create it.

### Base Idea
The base idea is to use a `freemarker` template which has a mock `svg` file and
fill in all the required values obtained from the configuration to it, then render the
template. This makes sure that we generate consistent and clean `svg` files.
Once we have this `svg` file, we can convert it to any image format such as `png`, `jpg`
etc.

The coloring and the percentage covered will be retrieved from the `csv` generated by
jacoco report. The path of the report will be provided by user. It will then read
`missed branches` and `missed instructions` as per the config and will calculate percentage.

### Code overview
The project structure is as follows:
```bash
├───src/
│   └───main/
│       ├───java/         # java source files
│       └───resources/    # freemakrer tempaltes
├───.editorconfig         # config utlity for editors
├───.gitattributes        # git config for the project
├───.gitignore            # ignore files for git
├───.travis.yml           # travis ci/cd config file
├───checkstyle.xml        # checkstyle config for code quality
├───CONTRIBUTING.md
├───LICENSE
├───pom.xml               # maven config file
└───README.md
```

**Java**
* `org.gahan.MyMojo`: This is the entry point for the `maven` plugin.
It reads the user configuration and then initializes the required class.
Once it has all the information, it renders the `freemarker` template.  
*Refer to java docs for more info on each class*

**Resources**
* `org.gahan.templates.svg-badge-template.ftl`:
`freemarker` template for svg file which will be rendered through the mojo class.

### Dependencies
* `org.apache.maven:maven-plugin-api:3.5.4`:
Dependency for creating `maven` plugin. Also the packaging for maven project has
to be `maven-plugin`
* `com.opencsv:opencsv:4.2`:
To load and read `csv` files. Although we can directly load the files
and read line by line by comma separated splits, but would still prefer
more robust library solution.
* `org.apache.pdfbox:pdfbox:2.0.4`:
The sole purpose of to be able to calculate width and height of badge
based on the content of badge key and coverage percentage. Since badge key can be
large this will help us calculate approx. width of the badge.
* `org.freemarker:freemarker:2.3.28`:
To deal with freemarker templates.


## Branching Conventions
The issues can broadly classified into 3 categories.
1. Feature: A new functionality which needs or requested to be introduced.
2. Bug: An unexpected behavior of the functionality
3. Task: Routine maintenance task such as adding documentation, upgrading dependencies etc.

For all the different types the branch name should be prefixed by `type` of issue plus the `id` of the issue
followed by `/` and then make sure the description is in `kebab-case`

```
<type>-<id>/<description-in-kebab-case>
```

For e.g. if you have following issues
* Feature: Add Custom Feature `#10`
* Bug: This is not working `#4`
* Task: Add more docs `#11`

then corresponding branch name will be like these

```
feature-10/add-custom-feature
bug-4/this-is-not-working
task-11/add-more-docs
```

Make sure you are using correct `id` for the issue.

## Commit Message Conventions
Commit message also follows similar conventions of using a fix prefix based on the type.
> Make sure to include issue `#id` in all the commit message to reference it correctly to an issue, followed by comma.

```
<Type> #<id>, Regualr Commit Message Description
```

For e.g. if you are working on a feature for which you want to commit,  
it may look like this

```
Feature #10, Added Custom feature
```
Notice the first letter in the message is in caps. i.e.
* for feature it will be `Feature`
* for bug it will be `Bug`
* for task it will be `Task`

Feel free to drop a mail on : gahan94rakh@gmail.com if you have more queries.  
Happy Coding !!