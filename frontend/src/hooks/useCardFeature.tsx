import { useState, useEffect } from 'react';
import { API } from 'aws-amplify';
import { CardFeature } from '../types/CardFeature';

export const useCardFeatures = () => {
  const [features, setFeatures] = useState<CardFeature[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchFeatures = async () => {
      try {
        const response = await API.get('CardFeaturesAPI', '/features', {});
        setFeatures(response);
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch features');
        setLoading(false);
      }
    };

    fetchFeatures();
  }, []);

  return { features, loading, error };
};
