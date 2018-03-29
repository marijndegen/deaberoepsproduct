SELECT subscriptions.*
FROM activesubscriptions
INNER JOIN subscriptions ON activesubscriptions.subscription_id = subscriptions.id
WHERE activesubscriptions.user_id = 1