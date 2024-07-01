import React from 'react';
import { withAuthenticator } from '@aws-amplify/ui-react';
import { useCardFeatures } from './hooks/useCardFeatures';
import '@aws-amplify/ui-react/styles.css';

const App: React.FC = () => {
  const { features, loading, error } = useCardFeatures();

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="App">
      <h1>Card Features</h1>
      {features.map((feature) => (
        <div key={feature.featureId}>
          <h2>{feature.name}</h2>
          <p>{feature.description}</p>
          <p>Permission: {feature.permission}</p>
        </div>
      ))}
    </div>
  );
};

export default withAuthenticator(App);
