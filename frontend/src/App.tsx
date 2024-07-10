import React, { useState, useEffect } from 'react';
import { API } from 'aws-amplify';

interface CardFeature {
  featureId: string;
  name: string;
  description: string;
  permission: string;
}

const App: React.FC = () => {
  const [features, setFeatures] = useState<CardFeature[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetchFeatures();
  }, []);

  const fetchFeatures = async () => {
    try {
      setLoading(true);
      const response = await API.get('CardFeaturesAPI', '/features', {});
      setFeatures(response);
      setLoading(false);
    } catch (err) {
      console.error('Error fetching features:', err);
      setError('Failed to fetch features');
      setLoading(false);
    }
  };

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

export default App;