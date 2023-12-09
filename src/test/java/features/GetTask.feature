
  Feature: Get details of Task

    Scenario Outline: Verify the task details to validate response code
      Given I hit the url of jira api endpoint
      When I pass the url of the task api endpoint of the Issue <IssueId>
      Then I receive the response code as 200
      Examples:
        | IssueId |
        | AOP-21  |

    Scenario Outline: Verify the task details to validate response body
      Given I hit the url of jira api endpoint
      When I pass the url of the task api endpoint of the Issue <IssueId>
      Then I receive the response body IssueType as <IssueType>
      And I receive the response body assignee as <Assignee>
      And I receive the response body of Issue Status as <IssueStatus>
      Examples:
        | IssueId | IssueType |  | Assignee  |  | IssueStatus |
        | AOP-20  | Test Task |  | Phuong Le |  | To Do       |
        | AOP-21  | Bug       |  | Phuong Le |  | Done        |