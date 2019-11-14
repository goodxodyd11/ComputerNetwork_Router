package ipc;

import java.util.ArrayList;

public class ChatAppLayer implements BaseLayer {
    public int nUpperLayerCount = 0;
    public String pLayerName;
    public BaseLayer p_UnderLayer = null;
    public ArrayList<BaseLayer> p_aUpperLayer = new ArrayList<>();
    private ChatAppHeader m_sHeader = new ChatAppHeader();

    private class ChatAppHeader {
        byte[] capp_totlen;
        byte capp_type;
        byte capp_unused;
        byte[] capp_data;

        public ChatAppHeader() {
            this.capp_totlen = new byte[2];
            this.capp_type = 0x00;
            this.capp_unused = 0x00;
            this.capp_data = null;
        }
    }//내부 클래스

    private byte[] totalLength(int lengthOfStr) {
        byte[] totalLength = new byte[2];
        totalLength[0] = (byte) ((lengthOfStr & 0xFF00) >> 8);
        totalLength[1] = (byte) (lengthOfStr & 0xFF);
        return totalLength;
    }

    public ChatAppLayer(String pName) {
        // super(pName);
        pLayerName = pName;
        ResetHeader();
    }

    private void ResetHeader() {//header를 모두 0으로 초기화
        for (int i = 0; i < 2; i++) {
            m_sHeader.capp_totlen[i] = (byte) 0x00;
        }
        m_sHeader.capp_type = 0x00;
        m_sHeader.capp_unused = 0x00;
        m_sHeader.capp_data = null;
    }

    @Override
    public boolean send(byte[] input, int length) {
        byte[] totalLength = this.totalLength(length);
        byte type = 0x00;
        byte[] sendData = this.objectToByte(input, length, totalLength, type);

        return this.getUnderLayer().send(sendData, sendData.length);
    }

    private byte[] objectToByte(byte[] input, int length, byte[] totlen, byte type) {
        byte[] sendData = new byte[length + 4];
        sendData[0] = totlen[0];
        sendData[1] = totlen[1];
        sendData[2] = type;
        sendData[3] = 0x00;

        if (length >= 0) {
            System.arraycopy(input, 0, sendData, 4, length);
        }

        return sendData;
    }

    private byte[] removeCappHeader(byte[] input, int length) {
        byte[] finalByteArray = new byte[length - 4];
        System.arraycopy(input, 4, finalByteArray, 0, length - 4);
        return finalByteArray;// 변경하세요 필요하시면
    }

    public synchronized boolean receive(byte[] input) {
        return this.getUpperLayer(0).receive(this.removeCappHeader(input, input.length));
        // 주소설정
    }

    @Override
    public String getLayerName() {
        return pLayerName;
    }

    @Override
    public BaseLayer getUnderLayer() {
        if (p_UnderLayer == null)
            return null;
        return p_UnderLayer;
    }

    @Override
    public BaseLayer getUpperLayer(int nindex) {
        if (nindex < 0 || nindex > nUpperLayerCount || nUpperLayerCount < 0)
            return null;
        return p_aUpperLayer.get(nindex);
    }

    @Override
    public void setUnderLayer(BaseLayer pUnderLayer) {
        if (pUnderLayer == null)
            return;
        this.p_UnderLayer = pUnderLayer;
    }

    @Override
    public void setUpperLayer(BaseLayer pUpperLayer) {
        if (pUpperLayer == null)
            return;
        this.p_aUpperLayer.add(nUpperLayerCount++, pUpperLayer);//layer추가
    }

    @Override
    public void setUpperUnderLayer(BaseLayer pUULayer) {
        this.setUpperLayer(pUULayer);
        pUULayer.setUnderLayer(this);
    }
}