## Content
1. UI tests
`src/test/java/tests/ui/UITest.java`
2. API tests
`src/test/java/tests/api/APITest.java`

## From my side
1. `Requirements for 1 and 2 tests: Should be realized in one test-class` - i didn't get what it mean, if all funcs, and sub funcs put in one class it will unreadable, coz i implement UI task by PageObjectModel, if i was wrong and all of that should be implemented in one class pls ask me i will update it
2. `Check that user doesn't have Shopping lists` - how it can be happened, if we have base shopping list which could not be deleted ?
3. `Verify that code reeponse = 200` - it looks like mistake, cuz if get deleted list it will return 400 (and at usual case if object don't exists should be returned 400)