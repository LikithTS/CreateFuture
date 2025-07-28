# Tech Test

You have been provided with an in-development Android app. The application uses an API to display lists of data about characters from the show "Game of Thrones". The project has some bugs and notable UI mismatches compared to the given designs.

## Some issues that have been reported

- App crashes on launch
- Major discrepancies with the designs e.g. white padding

## Improvements required

- A new feature needs to be added that would allow a user to search the list by character name.

## Resources

The API endpoint is available from:
[https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com/characters](https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com/characters)
Requests to that endpoint will require the following header:
"Authorization": "Bearer 754t!si@glcE2qmOFEcN"

Designs: 

![img_design_1.png](app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fimg_design_1.png) ![img_design_2.png](app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fimg_design_2.png)

## Criteria on which we will assess your submission

- Closeness to designs (pragmatism is encouraged and pixel perfection is NOT required)
- Code quality, included but not limited to, design patterns and organisation of the application code
- Scalability
- Error handling
- Unit tests

## Submission details

We would like you to fix the app's user facing issues (both documented and undocumented), add the additional search feature and submit the codebase in a more scalable state.

Please use version control. Import the supplied code as is to git and commit your changes through that. This will allow us to review the changes you have made.

We expect you should spend no more than 3 hours on this work. We appreciate you taking the time to work on this and understand that sometimes it's not possible to spend as much time as you would like. If there are any aspects of the codebase you would have liked to work on with more time, please detail these in the ReadME file to give us some insight in to your process.


## Findings

1. Initial app did not follow clean architecture or proper MVVM format. 
2. UI used was combination of fragment and some compose code which is not great. 
3. Even tho project looks simple. Having everything in one class is not good approach. 

## Improvements carried out

1. Deleted old classes and improved with jetpack compose
2. Followed clean architecture
3. Created use case for proper usage for future. For example if any api returns more data which is not required in mobile app screens. We can business logic in use case. 
4. In current example use case is not doing much, but it can used to enhance it for future purpose. 
5. Added search view in screen.
6. Added few unit test cases. 

## Future improvements

1. Seasons shows does not give proper info. Need to re-think on the design and layout. So it'll be good to have some info on it. 
2. Improve app to use dark and light theme. 
3. Need to handle different screen size. Need to support tablet and phone. Need a design to implement that. 
4. We can add some more unit test case for api failure. 
5. We can add some more sample for test case and update unit test with different cases. 
6. Move all dependency to lib.versions.toml. So it will be easy to handle version when we start with modular design.
7. We can create new modules when app start growing more with new features. 
8. All modules will be like a plug and play module with no concrete dependency to platforms. 
9. If we above step is followed then easily we can move our project to kotlin multi platform (KMP) in coming days. 
10. Since google is officially supporting KMP atm. We can see it will grow more faster in coming days.

## Important note

1. I won't suggest having a UI test cases. We can cover UI test case using automation. 
2. When we use automation for UI test cases. Then we don't have to connect real device for running. 
3. We can use appium and browser stack to run app on each push and make sure we're passing all the E2E automation testing. 
4. If any one test case fails we can block the PR merge. 
5. We can also add Unit test case coverage check.
6. Lint checks.
7. Check on sync issues. Whenever new PR is raised. 
8. If all the above steps is green only then PR will be allowed to be merged. 

## Clone the repository

Using **Android Studio:**

- Go to **File → New → Project from Version Control → Git**.
- Enter the repository URL:

## Support
Reach out likith.ts@gmail.com in case of any help.

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.


## Branching strategies

The main branch will act as a protected branch and the single source of truth. Nothing should be pushed directly to the main branch.
New changes need to be branched out from the main branch, and a pull request should be raised with details of the changes.

## Project status
Development complete. Review pending. 