include wpa-supplicant.inc

SRC_URI += "file://defconfig-ath6kl \
            "

DEPENDS += "qmi"

do_configure() {
    install -m 0644 ${WORKDIR}/defconfig-ath6kl .config
}

do_configure:append() {
    echo "CFLAGS += -I${WORKSPACE}/wlan/include" >> .config
    echo "CFLAGS += -I${WORKSPACE}/wlan/host/include" >> .config
    echo "CFLAGS += -I${WORKSPACE}/wlan/host/os/linux/include" >> .config
    echo "CFLAGS += -I${WORKSPACE}/wlan/host/wlan/include" >> .config
}
