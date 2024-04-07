describe('Sign up form end to end', () => {
  it('It cant create a user when passwords are not alike', () => {
    // Visit the page with the contact form
    cy.visit('http://localhost:5173/signup'); // Replace with the actual route

    // Set values for the input fields
    cy.get('#first_name').type('John');
    cy.get('#last_name').type('Doe');
    cy.get('#username').type('johndoe');
    cy.get('#password').type('password');
    cy.get('#repeatPassword').type('passwordd');
    cy.get('#email').type('john.doe@example.com');

    // Check for "Passwords do not match" text
    cy.contains('Passwords do not match');

    // Clear the email field to make the form invalid
    cy.get('#repeatPassword').clear();
    cy.get('#repeatPassword').type('password');

    // Check if the button is disabled when the form is invalid
    cy.get('button').should('not.be.disabled');

  });
  it('It cant create a user when the user already exists', () => {
    // Visit the page with the contact form
    cy.visit('http://localhost:5173/signup'); // Replace with the actual route

    // Set values for the input fields
    cy.get('#first_name').type('John');
    cy.get('#last_name').type('Doe');
    cy.get('#username').type('johndoe');
    cy.get('#password').type('password');
    cy.get('#repeatPassword').type('password');
    cy.get('#email').type('john.doe@example.com');


    // Check if the button is disabled when the form is invalid
    cy.get('button').should('not.be.disabled');
    cy.contains('button', 'Sign up').click();
    cy.contains('Username already exists');

  });

});
