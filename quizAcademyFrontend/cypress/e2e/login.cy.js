describe('Log in form end to end', () => {
    it('Login attempt, with wrong password', () => {
        // Visit the page with the contact form
        cy.visit('http://localhost:5173/login'); // Replace with the actual route

        cy.get('#username').type('johndoe');
        cy.get('#password').type('passwordd');

        // Check if the button is disabled when the form is invalid
        cy.get('button').should('not.be.disabled');
        cy.contains('button', 'Login').click();

        cy.contains('Username and password is incorrect');

    });
    it('Login attempt, with wrong username', () => {
        // Visit the page with the contact form
        cy.visit('http://localhost:5173/login'); // Replace with the actual route

        cy.get('#username').type('johndoee');
        cy.get('#password').type('password');

        // Check if the button is disabled when the form is invalid
        cy.get('button').should('not.be.disabled');
        cy.contains('button', 'Login').click();

        cy.contains('Username and password is incorrect');
    });
    it('Login attempt, should succeed', () => {
        // Visit the page with the contact form
        cy.visit('http://localhost:5173/login'); // Replace with the actual route

        cy.get('#username').type('johndoe');
        cy.get('#password').type('password');

        // Check if the button is disabled when the form is invalid
        cy.get('button').should('not.be.disabled');
        cy.contains('button', 'Login').click();

        cy.contains('Welcome to Quiz Academy! Here you can take quizzes, create your own quizzes and much more!');
    });

    it('Login attempt, and logout', () => {
        // Visit the page with the contact form
        cy.visit('http://localhost:5173/login'); // Replace with the actual route

        cy.get('#username').type('johndoe');
        cy.get('#password').type('password');

        // Check if the button is disabled when the form is invalid
        cy.get('button').should('not.be.disabled');
        cy.contains('button', 'Login').click();

        cy.contains('Welcome to Quiz Academy! Here you can take quizzes, create your own quizzes and much more!');
        // Find and click the button with the text "johndoe"
        cy.contains('button', 'johndoe').click();

        // After the "johndoe" button is clicked, a "logout" button appears
        // Find and click the "logout" button
        cy.contains('button', 'Logout').click();
    });

});
