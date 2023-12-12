
  Feature: Update the Test execution using zephyr apis

    Scenario Outline: Verify and perform update of the Test Execution using zephyr apis
      Given I hit the url of zephyr api endpoint
      When I pass the url of the post task api endpoint of <TestCase>, <TestCycle> for updating status as <TestStatus>
      Then I receive the response code as 201
      Examples:
        | TestCase | TestCycle | TestStatus |
        | AOP-T4   | AOP-R5    | Pass       |
        | AOP-T5   | AOP-R5    | Fail       |

