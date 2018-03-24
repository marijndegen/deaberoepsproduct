SELECT SUM(subscriptions.price)
FROM users
INNER JOIN activesubscriptions ON users.id = activesubscriptions.user_id
INNER JOIN subscriptions ON activesubscriptions.subscription_id = subscriptions.id
GROUP BY users.id
HAVING users.id = 1