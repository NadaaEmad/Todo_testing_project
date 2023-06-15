*** Settings ***
Library           SeleniumLibrary

*** Variables ***
${newTodo}        study
${description}    study for finals

*** Test Cases ***
ADD
    Open Browser    file:///D:/FCAI/Fourth%20year/Second%20term/Software%20testing/Assignment%203/Software-Testing-Assi3-Frontend/todo.html    Chrome
    Maximize Browser Window
    Input Text    id=todo    ${newTodo}
    Input Text    id=desc    ${description}
    Click Button    xpath=//*[@id="todo-form"]/button
    Wait Until Page Contains Element    id=todo-table
    Sleep    3
    Table Should Contain    id=todo-table    ${newTodo}
    ${fieldtodo}=    Get Text    id=todo
    ${fielddesc}=    Get Text    id=desc
    Should be Equal    ${fieldtodo}    ${EMPTY}
    Should be Equal    ${fielddesc}    ${EMPTY}
    Sleep    3
    Close Browser

DELETE
    #make sure to delete any prevoius todos before running this test case
    Open Browser    file:///D:/FCAI/Fourth%20year/Second%20term/Software%20testing/Assignment%203/Software-Testing-Assi3-Frontend/todo.html    Chrome
    Maximize Browser Window
    Input Text    id=todo    ${newTodo}    #add a todo to make sure that todos is not empty
    Input Text    id=desc    ${description}
    Click Button    xpath=//*[@id="todo-form"]/button
    Sleep    3
    Wait Until Page Contains Element    xpath=//*[@id="row-1"]/td[5]/button
    Click Button    xpath=//*[@id="row-1"]/td[5]/button
    Wait Until Page Does Not Contain    id=row-1
    Sleep    3
    Close Browser

UPDATE
    #make sure to delete any prevoius todos before running this test case
    Open Browser    file:///D:/FCAI/Fourth%20year/Second%20term/Software%20testing/Assignment%203/Software-Testing-Assi3-Frontend/todo.html    Chrome
    Maximize Browser Window
    Input Text    id=todo    ${newTodo}    #add a todo to make sure that todos is not empty
    Input Text    id=desc    ${description}
    Click Button    xpath=//*[@id="todo-form"]/button
    Wait Until Page Contains Element    id=todo-table
    Sleep    3
    Click Element    id=checkbox-1
    Checkbox Should Be Selected    id=checkbox-1
    Sleep    3
    Close Browser

VERIFY_ALL
    Open Browser    file:///D:/FCAI/Fourth%20year/Second%20term/Software%20testing/Assignment%203/Software-Testing-Assi3-Frontend/todo.html    Chrome
    Maximize Browser Window
    Wait Until Page Contains Element    id=todo-table
    ${cellCount}=    Get Element Count    class:form-check-input    #any todo must have a checkbox
    Click Button    xpath=/html/body/div/div/div[2]/button[1]
    Wait Until Page Contains Element    id=todo-table
    ${allCount}=    Get Element Count    class:form-check-input
    Should Be Equal As Numbers    ${cellCount}    ${allCount}
    Sleep    3
    Close Browser

VERIFY_COMPLETED
    Open Browser    file:///D:/FCAI/Fourth%20year/Second%20term/Software%20testing/Assignment%203/Software-Testing-Assi3-Frontend/todo.html    Chrome
    Maximize Browser Window
    Click Button    xpath=/html/body/div/div/div[2]/button[2]
    Wait Until Page Contains Element    id=todo-table
    ${allCheck}=    Get WebElements    class:form-check-input
    FOR    ${i}    IN    @{allCheck}
        Checkbox Should Be Selected    ${i}
    END
    Sleep    3
    Close Browser
