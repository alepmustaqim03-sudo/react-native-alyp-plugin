import React from 'react';
import { requireNativeComponent, ViewProps } from 'react-native';

export type SecurePanelProps = ViewProps & {
  title?: string;
  actionLabel?: string;
  onSecureAction?: (e: any) => void;
};

const RCTSecurePanel = requireNativeComponent<SecurePanelProps>('SecurePanel');
// name must match your Android ViewManager.getName()

export default function SecurePanel(props: SecurePanelProps) {
  return <RCTSecurePanel {...props} />;
}
