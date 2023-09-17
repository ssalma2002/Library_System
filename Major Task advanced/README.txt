to run our program on intellij 
1) after opening the folder go to File -> Project structure -> Libraries -> (+) -> Java then select the path of the lib inside the openjfx
folder 

2) try to run the program , an error will appear for missing components

3) go to Run -> edit configurations -> then copy this sentence in the add VM options
(if add VM options isn't there , press on the modify options and select it to see it)

Linux/Mac: --module-path /path/to/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml

Windows: --module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml


change the path of the inside double quotes to be the path of the lib file inside openjfx folder