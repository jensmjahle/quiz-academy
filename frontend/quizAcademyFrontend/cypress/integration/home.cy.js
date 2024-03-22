describe('template spec', () => {

  beforeEach(
    () => {
      cy.visit('/')
    }
  )

  it('passes', () => {
    cy.get('p').should('have.text', 'Welcome to the main page!')
  })
/*
  it('displays the component correctly', () => {
    cy.visit('/calc')
    // Verify that the component is rendered
    cy.get('.calculator').should('exist');
    cy.get('input').should('exist');
    cy.get('button#calculate').should('exist');
  });

  it('allows calculation', () => {
    cy.visit('/calc')
    // Enter equation and calculate
    cy.get('input').type('2+2');
    cy.get('button#calculate').click();

    // Verify the result
    cy.contains('Result: 4').should('exist');
  });

  it('handles invalid input', () => {
    cy.visit('/calc')
    // Enter invalid equation
    cy.get('input').type('2a');
    cy.get('button#calculate').click();

    // Verify the error message
    cy.contains('Result: Error').should('exist');
  });

  it('allows adding numbers and operators', () => {
    cy.visit('/calc')
    // Click buttons to add numbers and operators
    cy.get('button#add1').click();
    cy.get('button#add2').click();
    cy.get('button#addplus').click();
    cy.get('button#add3').click();
    cy.get('button#calculate').click();

    // Verify the result
    cy.contains('Result: 6').should('exist');
  });

  it('clears the input', () => {
    cy.visit('/calc')
    // Enter equation and then clear it
    cy.get('input').type('2+2');
    cy.get('button#clearinput').click();

    // Verify that input is cleared
    cy.get('input').should('have.value', '');
  });*/
})