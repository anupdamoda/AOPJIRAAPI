
  Feature: Update Status of the Task

    Scenario Outline: Verify to perform update of the status of the task
      Given I hit the url of jira api endpoint
      When I pass the url of the post task api endpoint of the Issue <IssueId> for updating status
      Then I receive the response code as 204
      Examples:
        | IssueId |
        | AOP-20  |

