import React from 'react';
import { SafeAreaView, Button, Alert } from 'react-native';
import { SecurePanel } from 'react-native-alyp-plugin';
import Alyp from 'react-native-alyp-plugin';

export default function App() {
  return (
    <SafeAreaView style={{ flex: 1, padding: 16 }}>
      <SecurePanel
        title="Hello from AAR"
        actionLabel="Sign"
        onSecureAction={() => Alert.alert('Pressed', 'Native button tapped')}
        style={{ height: 140, borderWidth: 1 }}
      />
      <Button
        title="Open Secure Screen"
        onPress={() => Alyp.showSecureScreen()}
      />
    </SafeAreaView>
  );
}
