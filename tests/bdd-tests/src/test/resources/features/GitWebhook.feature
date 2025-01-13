Feature: Git Webhook Handling

	Scenario: Valid Git Webhook Event is handled
		Given a valid Git Webhook request
		When the request is sent to "/api/v1/git/webhook"
		Then the API should return a success response with branchId
