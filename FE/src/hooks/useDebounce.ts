import { useEffect, useState } from 'react';

interface useDebounceProps {
  stateValue: string;
  delay: number;
}

export const useDebounce = ({ stateValue, delay }: useDebounceProps): string => {
  const [value, setValue] = useState(stateValue);

  useEffect(() => {
    console.log('debounce start');
    const timer = setTimeout(() => {
      setValue(stateValue);
    }, delay);

    return () => {
      clearTimeout(timer);
    };
  }, [stateValue]);

  return value;
};
